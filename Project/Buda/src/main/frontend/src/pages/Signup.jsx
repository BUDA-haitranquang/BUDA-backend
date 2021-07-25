import react, { Component, Fragment } from 'react';
import "./Signup.css"
import ava from "../img/ava.png"
export default class Signup extends Component {
    render(){
        return(
            <Fragment>
            <div className="container">
                <div className="ava">
                    <img src={ava}/>
                </div>
                <div className="form">
                    <ul>
                    <li><input type="text" placeholder="First Name" /></li>
                    <li><input type="text" placeholder="Last Name" /></li>
                    <li><input type="text" placeholder="Email" /></li>
                    <li><input type="text" placeholder="Phone" /></li>
                    <li><input type="password" placeholder="Password" /></li>
                    <li><input type="password" placeholder="Reenter Password" /></li>
                    </ul>
                    <div className="clearfix"></div>
                    <input type="submit" onClick="isRememberMe" value="Signup"/>
                </div>
            </div>
        </Fragment>
        )
      
    }
  }