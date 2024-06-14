'use client'

import {useRouter} from "next/navigation";
import {useContext, useEffect, useState} from "react";
import styles from './Product.module.css'
import {TokenContext} from "@/app/layout";

export default function Product() {
    const router = useRouter();
    const {token} = useContext(TokenContext)
    //

    const [formData, setFormData] = useState([{
        id: '',
        name: '',
    }]);


    useEffect(() => {
        if (token) {
            loadData();
        }
    }, [token]);

    const loadData = async () => {
        fetch('http://localhost:8080/products', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        }).then(response => {
            if (!response.ok) {
                return response.json().then(errorData => {
                    throw new Error(errorData.message || 'HTTP error!');
                });
            }
            return response.json();
        }).then(data => {
            console.log(data);
            setFormData(data)
        })
            .catch(error => {
                console.log(error);
            });
    }

    return (
        <div>
            <button className={styles.backButton} onClick={() => router.push("\\", undefined)}>Back</button>
            <h2>Product List</h2>
            <table className={styles.productList}>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                </tr>
                {formData?.map((product) => (
                    <>
                        <tr>
                            <td>{product.id}</td>
                            <td key={product.id} className={styles.productItem} onClick={() => alert("fe")}>
                                {product.name}
                            </td>
                        </tr>
                    </>

                ))}
            </table>
        </div>
    )

}