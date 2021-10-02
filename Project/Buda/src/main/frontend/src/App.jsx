import React, {useState, useEffect, Fragment} from 'react';
import './App.css';
import axios from 'axios';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useHistory
} from "react-router-dom";
import Login from './pages/Login';
import Signup from './pages/Signup';
function App() {

  return (
    <Router>
      <div>
        <nav className="main-nav">
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/about">About</Link>
            </li>
            {
              localStorage.getItem('user-info') ?
                <>
                  <li>
                    <Link to="/logout">Logout</Link>
                  </li>
                </>
                :
                <>
                  <li>
                    <Link to="/login">Login</Link>
                  </li>
                  <li>
                    <Link to="/signup">Sign Up</Link>
                  </li>
                </>
            }
            
          </ul>
        </nav>
        <div className="clearfix"></div>
        <div style={{"marginBottom" : "42px"}}></div>
        {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
        <Switch>
          <Route path="/about">
            <About />
          </Route>
          <Route path="/login">
            <Login></Login>
          </Route>
          <Route path="/logout">
            <Logout></Logout>
          </Route>
          <Route path="/signup">
            <Signup></Signup>
          </Route>
          <Route path="/home">
            <Home/>
          </Route>
          <Route exact path="/">
            <Home />
          </Route>
          <Route path="*">
            <NotFound />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

function Home() {
  return <h2>This is Home Page</h2>;
}

function About() {
  return(
    <Fragment>
      Chung em chao anh Tien a.
    </Fragment>
  ) 
}

function Logout(){
  localStorage.removeItem('user-info');
  return <Login/>
}

function NotFound(){
  return(
    <h1>404 Not Found</h1>
  )
}

export default App;