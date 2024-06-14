"use client"
import {Inter} from "next/font/google";
import "./globals.css";
import {createContext, useEffect, useState} from "react";
import {useRouter} from "next/navigation";
import styles from "./page.module.css";
import {jwtDecode} from "jwt-decode";

const inter = Inter({subsets: ["latin"]});

export const TokenContext = createContext(null);
export default function RootLayout({children}) {
    const [token, setToken] = useState('');
    const [tokenInfo, setTokenInfo] = useState('');

    const [isHovered, setIsHovered] = useState(false);
    const router = useRouter()
    useEffect(() => {
        const storedToken = sessionStorage.getItem('token');
        if (storedToken) {
            console.log("token")
            setToken(storedToken)
            const decodedToken = jwtDecode(storedToken);
            setTokenInfo(decodedToken);
        } else {
            console.log("no token")
            router.push("/", undefined)
        }
    }, []);

    useEffect(() => {
        sessionStorage.setItem('token', token)
        if(token){
            const decodedToken = jwtDecode(token);
            setTokenInfo(decodedToken);
        }
        router.push("/", undefined)
    }, [token]);

    const hover = () =>{
        const storedToken = sessionStorage.getItem('token');
        if (storedToken) {
        return (
                JSON.stringify(tokenInfo.roles)
        )}else return ""
    }
    const setTokenEmpty = () =>{
        setToken("")
        setTokenInfo("")
        sessionStorage.setItem("token", "")
    }
    return (
        <html lang="en">
        <body className={styles.main}>
        <p
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
        >
           {isHovered && token? hover() :  <>Status: {token ? 'logged-in' : 'logged-out'}</>}
        </p>
        <TokenContext.Provider value={{token, setToken, tokenInfo, setTokenEmpty }}>
            {children}
        </TokenContext.Provider></body>
        </html>
    );
}
