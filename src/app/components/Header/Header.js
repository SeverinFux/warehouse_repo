import { useContext, useState } from "react";
import styles from "./Header.module.css";
import { useRouter } from "next/navigation";
import { TokenContext } from "@/app/layout";
import {log} from "next/dist/server/typescript/utils";

const data = [
    {
        title: 'Home',
        link: '/',
    },
    {
        title: 'User Profile',
        link: '/user-profile',
    },
    {
        title: 'Product',
        link: '/product',
        role: ['ROLE_ADMIN', 'ROLE_STAFF']
    }
];

const Header = () => {
    const {tokenInfo, setTokenEmpty } = useContext(TokenContext);
    const [navs] = useState(data);
    const router = useRouter();

    function onClickHeader(nav) {
        router.push(nav.link, undefined);
    }

    const logout = () => {
        console.log("logout");
        setTokenEmpty()
    }


    const filteredNavs = navs.filter(nav => {
        const rolesFromUser = tokenInfo?.roles;
        if (rolesFromUser && nav.role) {
            const hasRole = rolesFromUser.some(obj => {
                console.log(obj.authority);
                const includesRole = nav.role.includes(obj.authority);
                console.log(includesRole);
                return includesRole;
            });
            return hasRole;
        } else {
            return true;
        }
    });


    return (
        <div className={styles.header}>
            {filteredNavs.map((nav, index) => (
                <button key={index} className={styles.navButton} onClick={() => onClickHeader(nav)}>
                    {nav.title}
                </button>
            ))}
            <button key={"logout"} onClick={logout} className={`${styles.navButton} ${styles.logout}`}>
                Logout
            </button>
        </div>
    );
}

export default Header;
