// app/components/Auth.js
"use client";
import { useContext } from "react";
import { TokenContext } from "@/app/layout";
import LoginForm from "@/app/components/Login/LoginForm";
import Header from "@/app/components/Header/Header";

export default function Auth({ children }) {
    const { token, setToken } = useContext(TokenContext);

    return token ? (
        <>
            <Header />
            {children}
        </>
    ) : (
        <LoginForm setToken={setToken}/>
    );
}
