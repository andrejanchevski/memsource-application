import React from 'react';
import './App.css';
import {Routes, BrowserRouter as Router, Route, Navigate} from "react-router-dom";
import LoginPage from './containers/LoginPage';
import ProjectsPage from "./containers/ProjectsPage";

function App() {
  return (
      <div>
        <Router>
          <Routes>
              <Route path="/projects" element={<ProjectsPage/>}/>
              <Route path="/login" element={<LoginPage/>}  />
              <Route path="/" element={<Navigate replace to="/login"/>}/>
          </Routes>
        </Router>
      </div>
  );
}

export default App;
