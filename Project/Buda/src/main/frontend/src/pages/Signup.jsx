import react, { Component, Fragment } from 'react';
import "./Signup.css"
import ava from "../img/ava.png"
import { Redirect } from 'react-router-dom';
export default class Signup extends Component {
    constructor(props) {
        super(props);
        this.state={
            firstname: '',
            lastname: '',
            email: '',
            phone: '',
            password: '',
            checkpassword: ''
        }
    }
    setFirstName = e =>{
        this.setState({firstname: e.target.value})
    }
    setLastName = e =>{
        this.setState({lastname: e.target.value})
    }
    setEmail = e =>{
        this.setState({email: e.target.value})
    }
    setPhone = e =>{
        this.setState({phone: e.target.value})
    }
    setPassword = e =>{
        const pass = e.target.value;
        if(pass.length < 6){
            console.log("Password is too short");
        }
        else this.setState({password: pass})
    }
    setCheckpassword = e =>{
        this.setState({checkpassword: e.target.value})
    }
    checkPasswordDuplicate = ()=>{
        console.log(this.state.password);
        console.log(this.state.checkpassword);
        if(this.state.checkpassword !== this.state.password)
        return(
            <p style={{color: 'red', textAlign: 'left', marginLeft: '5%'}}>* Passwords are not identical</p>
        )
        
    }
    submitRegister = () =>{
        console.log(this.state);
        
    }
    render(){
        return(
            <Fragment>
            <div className="signup-container">
                <div className="ava">
                    <img src={ava}/>
                </div>
                <form>
                    <ul>
                    <li><input type="text" placeholder="First Name" value={this.state.firstname} onChange={this.setFirstName} required/></li>
                    <li><input type="text" placeholder="Last Name" value={this.state.lastname} onChange={this.setLastName}/></li>
                    <li><input type="text" placeholder="Email" value={this.state.email} onChange={this.setEmail}/></li>
                    <li><input type="text" placeholder="Phone" value={this.state.phone} onChange={this.setPhone}/></li>
                    <li><input type="password" placeholder="Password" onChange={this.setPassword}/></li>
                    <li>
                        <input type="password" placeholder="Reenter Password" onChange={this.setCheckpassword}/>
                        {this.checkPasswordDuplicate()}
                    </li>
                    
                    </ul>
                    <div className="clearfix"></div>
                    <input type="submit" onClick={this.submitRegister} value="Signup"/>
                </form>
            </div>
        </Fragment>
        )
      
    }
  }