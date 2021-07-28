import react, { Component, Fragment, useEffect, useState } from 'react';
import "./Login.css"
import ava from "../img/ava.png"
import { useHistory } from 'react-router-dom';
import { postApi } from '../api/Api';
export default function Login () {
    const [inputs, setInputs] = useState({
        username: '',
        password: ''
    })
    const{username, password} = inputs;
    //const [isRemember, setIsRemember] = useState(false);
    const history = useHistory();
    useEffect(()=>{
        if(localStorage.getItem('user-info')){
            history.push("/")
        }
    }, [])
    function handleChange(e) {
        const{name, value} = e.target;
        setInputs(inputs=>({...inputs, [name]: value}))
    }

    function login(e){
        e.preventDefault();
        postApi(inputs);
        localStorage.setItem("user-info", JSON.stringify(inputs))
        //history.push("/")
    }
    
    return(
        <div className="login-container">
            <div className="ava">
                <img src={ava}/>
            </div>
            <form>
                <ul>
                <li><input type="text" placeholder="Email/Phone" name="username" onChange={e=>handleChange(e)} required/></li>
                <li><input type="password" placeholder="Password" name="password" onChange={e=>handleChange(e)} required/></li>
                </ul>
                {/* TODO: Add remember me */}
                {/* <label><input type="checkbox" value="isRemember" />Remember me</label> */}
                <span>Forgot Password?</span>
                <div className="clearfix"></div>
                <input type="submit" onClick={(e)=>login(e)} value="Login"/>
            </form>
        </div>
    )
}
        