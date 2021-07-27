import react, { Component, Fragment, useState } from 'react';
import "./Login.css"
import ava from "../img/ava.png"
export default function Login () {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [isRemember, setIsRemember] = useState(false);
    return(
        <div className="login-container">
            <div className="ava">
                <img src={ava}/>
            </div>
            <form>
                <ul>
                <li><input type="text" placeholder="Email/Phone" required/></li>
                <li><input type="password" placeholder="Password" required/></li>
                </ul>
                <label><input type="checkbox" value="isRemember" />Remember me</label>
                <span>Forgot Password?</span>
                <div className="clearfix"></div>
                <input type="submit" onClick="isRememberMe" value="Login"/>
            </form>
        </div>
    )
}
        