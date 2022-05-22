import React from 'react';
import './App.css';
import {Routes, BrowserRouter as Router, Route, Navigate} from "react-router-dom";
import LoginPage from './containers/LoginPage';
import ProjectsPage from "./containers/ProjectsPage";
import {UserContextProvider} from "./context/UserContext";
import {PagedProjectsContextProvider} from "./context/ProjectsContext";

function App() {
    return (
        <div>
            <UserContextProvider>
                <PagedProjectsContextProvider>
                    <Router>
                        <Routes>
                            <Route path="/projects" element={<ProjectsPage/>}/>
                            <Route path="/login" element={<LoginPage/>}/>
                            <Route path="/" element={<Navigate replace to="/login"/>}/>
                        </Routes>
                    </Router>
                </PagedProjectsContextProvider>
            </UserContextProvider>
        </div>
    );
}

export default App;
