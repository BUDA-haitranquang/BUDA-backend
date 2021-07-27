import react, { Component, Fragment, useState } from 'react';
import "./Login.css"
import ava from "../img/ava.png"
export default function Login () {
    const [inputs, setInputs] = useState({
        username: '',
        password: ''
    })
    const [submitted, setSubmitted] = useState(false)
    const{username, password} = inputs;
    //const [isRemember, setIsRemember] = useState(false);
    
    function handleChange(e) {
        const{name, value} = e.target;
        setInputs(inputs=>({...inputs, [name]: value}))
    }

    function handleSubmit(e){
        e.preventDefault();
        setSubmitted(true)
        if(username && password){
            
        }
    }
    return(
        <div className="login-container">
            <div className="ava">
                <img src={ava}/>
            </div>
            <form>
                <ul>
                <li><input type="text" placeholder="Email/Phone" value={inputs.username} onChange={handleChange} required/></li>
                <li><input type="password" placeholder="Password" required/></li>
                </ul>
                {/* TODO: Add remember me */}
                {/* <label><input type="checkbox" value="isRemember" />Remember me</label> */}
                <span>Forgot Password?</span>
                <div className="clearfix"></div>
                <input type="submit" onClick={()=>handleSubmit()} value="Login"/>
            </form>
        </div>
    )
}
        