import React, { useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, useNavigate } from 'react-router-dom';
import Login from './pages/Login';
import Signup from './pages/Signup';

function DefaultRoute() {
  let navigate = useNavigate();
  useEffect(() => {
    navigate('/login');
  }, [navigate]);

  return null;
}

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="*" element={<DefaultRoute />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
