'use client'
import {useContext, useEffect, useState} from 'react'
import styles from '../Login/LoginForm.module.css';
import {jwtDecode} from "jwt-decode";
import {TokenContext} from "@/app/layout";

export default function UserProfileForm() {
    const {token, setToken} = useContext(TokenContext)

    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        address: '',
        profilePictureUrl: '',
    });
    const [error, setError] = useState('');
    const [message, setMessage] = useState('');
    const [userData, setUserData] = useState(null);


    useEffect(() => {
        if (token) {
            const decodedToken = jwtDecode(token);
            setUserData(decodedToken);
            loadData();
        }
    }, [token]);

    const loadData = async () => {
        fetch('http://localhost:8080/user-profiles', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errorData => {
                        throw new Error(errorData.message || 'HTTP error!');
                    });
                }
                return response.json();
            })
            .then(data => {
                setMessage('Profile updated successfully!');
                setFormData(data)
                console.log(data);
            })
            .catch(error => {
                console.log(error);
                setError(error.message || 'Profile update failed');
            });
    }
    const handleSubmit = async (event) => {
        event.preventDefault();
        setError('');
        setMessage('');

        const token = sessionStorage.getItem('token');
        if (!token) {
            setError('You must be logged in to update your profile');
            return;
        }
        fetch('http://localhost:8080/user-profiles', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body:JSON.stringify(formData)
        })       .then(response => {
            if (!response.ok) {
                return response.json().then(errorData => {
                    throw new Error(errorData.message || 'HTTP error!');
                });
            }
            return response.json();
        })
    }
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    return (
        <div>
            <form onSubmit={handleSubmit} className={styles.form}>
                <h2 className={styles.title}>Edit Profile von {userData?.sub}</h2>
                <div className={styles.field}>
                    <label htmlFor="firstName">First Name</label>
                    <input
                        type="text"
                        id="firstName"
                        name="firstName"
                        value={formData.firstName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className={styles.field}>
                    <label htmlFor="lastName">Last Name</label>
                    <input
                        type="text"
                        id="lastName"
                        name="lastName"
                        value={formData.lastName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className={styles.field}>
                    <label htmlFor="address">Address</label>
                    <input
                        type="text"
                        id="address"
                        name="address"
                        value={formData.address}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className={styles.field}>
                    <label htmlFor="profilePictureUrl">Profile Picture URL</label>
                    <input
                        type="text"
                        id="profilePictureUrl"
                        name="profilePictureUrl"
                        value={formData.profilePictureUrl}
                        onChange={handleChange}
                    />
                </div>
                {error && <p className={styles.error}>{error}</p>}
                {message && <p className={styles.message}>{message}</p>}
                <button type="submit" className={styles.button}>
                    Save Changes
                </button>
            </form>
        </div>
    );
}
