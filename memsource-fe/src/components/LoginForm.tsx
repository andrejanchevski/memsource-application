const LoginForm = () => {
    return (
        <form className="w-50">
            <div className="form-group">
                <label htmlFor="loginEmailInput">Email address</label>
                <input type="email" className="form-control" id="loginEmailInput" aria-describedby="emailHelp"
                       placeholder="Enter email"/>
                <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone
                    else.</small>
            </div>
            <div className="form-group">
                <label htmlFor="loginEmailPassword">Password</label>
                <input type="password" className="form-control" id="loginEmailPassword" placeholder="Password"/>
            </div>
            <button type="submit" className="btn btn-primary mt-3">Submit</button>
        </form>
    )
}

export default LoginForm;