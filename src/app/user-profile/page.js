'use client'

import {useRouter} from "next/navigation";
import UserProfileForm from "@/app/components/UserprofileForm/UserProfileForm";

export default function UserProfilePage() {
    const router = useRouter()

    return (
        <div>
            <button onClick={()=>router.push("\\",undefined)}>Back</button>
            <UserProfileForm/>
        </div>
    )

}