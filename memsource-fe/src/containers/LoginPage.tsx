import LoginForm from "../components/LoginForm";

const LoginPage = (): JSX.Element => {

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <LoginForm/>
            </div>
        </div>
    )
}

export default LoginPage;