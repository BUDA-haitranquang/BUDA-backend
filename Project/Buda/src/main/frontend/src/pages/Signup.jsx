import react, { useState, Fragment } from 'react';
import "./Signup.css";
import ava from "../img/ava.png";
import { Redirect } from 'react-router-dom';
import postApi from '../api/Api';
export default function Signup() {
    const [firstname, setFirstname] = useState('')
    const [lastname, setLastname] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [phone, setPhone] = useState('')
    const [checkpassword, setCheckpassword] = useState('')
    
    const checkPasswordDuplicate = ()=>{
        if(checkpassword !== password) 
        return(
            <p style={{color: 'red', textAlign: 'left', marginLeft: '5%'}}>* Passwords are not identical</p>
        )
        else{}
    }
    //TODO: add condition for check Password
    const submitRegister = (e) =>{
        const item ={firstname, lastname, email, phone, password, checkpassword}
        console.log(item);  //remember to remove later
        postApi(item);
    }

    return(
        <Fragment>
        <div className="signup-container">
            <div className="ava">
                <img src={ava}/>
            </div>
            <form onSubmit={e => e.preventDefault()} >
                <ul>
                <li><input type="text" placeholder="First Name" value={firstname} onChange={(e)=>setFirstname(e.target.value)} required/></li>
                <li><input type="text" placeholder="Last Name" value={lastname} onChange={(e)=>setLastname(e.target.value)} /></li>
                <li><input type="email" placeholder="Email" value={email} onChange={(e)=>setEmail(e.target.value)} /></li>
                <li><input type="text" placeholder="Phone" value={phone} onChange={(e)=>setPhone(e.target.value)} /></li>
                <li><input type="password" placeholder="Password" value={password} onChange={(e)=>setPassword(e.target.value)} minLength='6' required/></li>
                <li>
                    <input type="password" placeholder="Reenter Password" value={checkpassword} onChange={(e)=>setCheckpassword(e.target.value)} required/>
                    {checkPasswordDuplicate()}
                </li>
                
                </ul>
                <div className="clearfix"></div>
                <input type="submit" onClick={()=>submitRegister()} value="Signup"/>
            </form>
        </div>
    </Fragment>
    )

  }