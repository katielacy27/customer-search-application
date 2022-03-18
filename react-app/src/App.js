import './App.css';
import CustomerTable from './components/CustomerTable';
import React from 'react';
import axios from 'axios';
import {
  BrowserRouter,
  useRoutes
} from "react-router-dom";

function App() {

  // react router 6 I learned doesnt let you use an array of paths to be passed as a Route property.
  // Instead you have to use the useRoutes hook So I wrapped the Route in a wrapper function
  const AppWrapper = () => useRoutes([
    { path: "/", element: <CustomerTable /> },
    { path: "/customers", element: <CustomerTable /> },
  ]);

  return (
    <div className="App">
      <header className="App-header">
      <h1>Customer Search</h1>
        <BrowserRouter>
          <AppWrapper />
        </BrowserRouter>
      </header>
    </div>
  )
}

export default App;
