'use client'
import {useState} from 'react'
import styles from './LoginForm.module.css';

export default function LoginForm({ token, setToken }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [passwordConfirm, setPasswordConfirm] = useState('');
    const [error, setError] = useState('');
    const [isRegister, setIsRegister] = useState(false);

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError('');
        if (isRegister && password === passwordConfirm || !isRegister) {
            const praefix = isRegister ? '/register' : '/login';

            fetch('http://localhost:8080/auth' + praefix, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({username, password})
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
                        setToken(data.jwt);
                        sessionStorage.setItem('token', data.jwt);

                    console.log(data);
                })
                .catch(error => {
                    console.log(error);
                    setError(error.message || (isRegister ? 'Registration failed' : 'Login failed'));
                });
        } else {
            setError("'Password' and 'Confirm Password' aren't equal");
        }
    }

    return (
        <div>
            <form onSubmit={handleSubmit} className={styles.form}>
                <div className={styles.switch}>
                    <p>Switch to <span
                        style={{fontWeight: "bolder"}}
                        onClick={() => setIsRegister(!isRegister)}>{isRegister ? 'Login' : 'Register'}</span></p>
                </div>

                <h2 className={styles.title}>{isRegister ? 'Register' : 'Sign In'}</h2>
                <div className={styles.field}>
                    <label htmlFor="username">Username</label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div className={styles.field}>
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                {isRegister && (
                    <div className={styles.field}>
                        <label htmlFor="passwordConfirm">Confirm Password</label>
                        <input
                            type="password"
                            id="passwordConfirm"
                            name="passwordConfirm"
                            value={passwordConfirm}
                            onChange={(e) => setPasswordConfirm(e.target.value)}
                            required
                        />
                    </div>
                )}
                {error && <p className={styles.error}>{error}</p>}
                <button type="submit" className={styles.button}>
                    {isRegister ? 'Register' : 'Sign In'}
                </button>
            </form>
        </div>
    );
}
