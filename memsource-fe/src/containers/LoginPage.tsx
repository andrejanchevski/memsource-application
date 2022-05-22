import React from "react";
import {logInUser} from "../api/ApiService";
import {AxiosResponse} from "axios";
import {UserAuthenticatedResponse} from "../domain/response/UserAuthenticatedResponse";
import {useActiveUserUpdateContext} from "../context/UserContext";
import {useNavigate} from "react-router-dom";


const LoginPage = (): JSX.Element => {

    const setActiveUser: Function = useActiveUserUpdateContext();
    const navigate = useNavigate();

    const handleLogIn = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const userName = (event.currentTarget.elements[0] as HTMLInputElement).value;
        logInUser({
                username: userName,
                password: (event.currentTarget.elements[1] as HTMLInputElement).value
            })
            .then((res: AxiosResponse<UserAuthenticatedResponse>) => {
                setActiveUser({
                    userName: userName,
                    authenticationToken: res.data.access_token,
                    active: true
                })
                localStorage.setItem('authenticationToken', res.data.access_token);
                navigate('/projects');
            })
            .catch((err)=> {

            })
    }

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <form className="w-50" onSubmit={handleLogIn}>
                    <div className="form-group">
                        <label htmlFor="loginEmailInput">Email address</label>
                        <input type="text" className="form-control" id="loginUserNameInput" aria-describedby="userNameHelp"
                               placeholder="Enter userName"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="loginEmailPassword">Password</label>
                        <input type="password" className="form-control" id="loginEmailPassword" placeholder="Password"/>
                    </div>
                    <button type="submit" className="btn btn-primary mt-3">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default LoginPage;