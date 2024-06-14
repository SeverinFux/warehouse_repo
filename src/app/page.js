"use client"
import Auth from "@/app/components/Auth/Auth";
import {useContext} from "react";
import {TokenContext} from "@/app/layout";


export default function Home() {
    const {token}  = useContext(TokenContext)
    return (
            <Auth>

            </Auth>
    );
}
