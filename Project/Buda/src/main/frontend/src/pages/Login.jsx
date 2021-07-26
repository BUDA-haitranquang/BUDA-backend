import react, { Component, Fragment } from 'react';
import "./Login.css"
import ava from "../img/ava.png"
export default class Login extends Component {
    render(){
        return(
            <Fragment>
            <div className="login-container">
                <div className="ava">
                    <img src={ava}/>
                </div>
                <div className="form">
                    <ul>
                    <li><input type="text" placeholder="Email/Phone" /></li>
                    <li><input type="password" placeholder="Password" /></li>
                    </ul>
                    <label><input type="checkbox" value="isRemember" />Remember me</label>
                    <span>Forgot Password?</span>
                    <div className="clearfix"></div>
                    <input type="submit" onClick="isRememberMe" value="Login"/>
                </div>
            </div>
        </Fragment>
        )
      
    }
  }