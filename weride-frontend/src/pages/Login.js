import React from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Login extends React.Component {
    state = {
        email: '',
        password: ''
    }

    handleInputChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }
    
    handleSubmit = (event) => {
        event.preventDefault();
    
        axios.post('http://localhost:8080/login', {
            email: this.state.email,
            password: this.state.password
        })
        .then(response => {
            // Assuming your response contains a token in response.data.token
            localStorage.setItem('token', response.data.token);

            // If you have a way to set the application state to authenticated, do it here
            // This might be a Redux action, or calling a function passed from a parent component

            console.log(response.data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        email:
                        <input type='text' name='email' onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <label>
                        Password:
                        <input type='password' name='password' onChange={this.handleInputChange} />
                    </label>
                    <br />
                    <input type='submit' value='Submit' />
                    <Link to="/signup"><button type="button">Signup</button></Link>
                </form>
            </div>
        );
    }
}

export default Login;
