import react, { Component, Fragment } from 'react';
import "./Login.css"
import ava from "../img/ava.png"
export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state={
            email: '',
            password: '',
            isRememberMe: false
        }
    }
    render(){
        return(
            <Fragment>
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
        </Fragment>
        )
      
    }
  }