import react, {Component, useState} from 'react';
export default class Register extends Component{
    constructor(props) {
        super(props);
        this.state = {
            id: '',
            firstname: '',
            lastname: '',
            email: '',
            phone: '',
            password: ''
        }
    }
    setFirstName = e=>{
        this.setState({firstname: e.target.value})
    }
    setLastName = e=>{
        this.setState({lastname: e.target.value})
    }
    setId = e=>{
        this.setState({id: e.target.value})
    }
    setEmail = e=>{
        this.setState({email: e.target.value})
    }
    setPhone = e=>{
        this.setState({phone: e.target.value})
    }
    setPassword = e=>{
        this.setState({password: e.target.value})
    }
    
    register = ()=>{
        console.log(this.state);    //just for testing 
        
    }
}