import React from 'react';
import axios from 'axios';

class Signup extends React.Component {
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
    
        axios.post('http://localhost:8080/signup', {
            email: this.state.email,
            password: this.state.password
        })
        .then(response => {
            console.log(response.data);

            // If you want to redirect the user to the login page after a successful signup, do it here
            // This depends on how you've set up your routing, but might look something like this:
            // this.props.history.push('/login');
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
                </form>
            </div>
        );
    }
}

export default Signup;
