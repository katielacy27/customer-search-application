import React, { useState } from 'react';
import { useSearchParams } from "react-router-dom";
import axios from 'axios'

function CustomerTable () {

  // since we want to manage state in the URL (enable sharing with other people)
  // we cant use the useState hook, instead we use the useSearchParams hook
  const [search, setSearch] = useSearchParams();

  const [customers, setCustomers] = useState([]);

  const [name, setName] = useState("");
  const [sortBy, setSortBy] = useState("")
  const [companyName, setCompanyName] = useState("");

  const handleNameChange = (event) => {
      setSearch({ name: event.target.value, sortBy: sortBy, companyName: companyName });
      setName(event.target.value);
  };

  const handleSortBy = (event) => {
      setSearch({ name: name, sortBy: event.target.value, companyName: companyName });
      setSortBy(event.target.value);
  };

  const handleCompanyNameChange = (event) => {
      setSearch({ name: name, sortBy: sortBy, companyName: event.target.value });
      setCompanyName(event.target.value);
  };

  // searchButton makes the call to the backend - when it is clicked the request is sent
  // optionally we can monitor when searchParams changes with useEffect. however this would
  // make a LOT of backend calls so I limited to just on button click
  const handleSearchCustomers = () => {
    const url = 'http://localhost:8080/';
    axios.get(`${url}v1/customers/?${search}`)
      .then((response) => {
        setCustomers(response.data);
      })
      .catch((error) => {
        // TODO print usage
        console.log(error);
      })
  };

  return (
    <div className="HeaderRow">
      <input
          type="text"
          placeholder="name"
          value={search.get('name')}
          onChange={handleNameChange}
      />

      <input
          type="text"
          placeholder="company name"
          value={search.get('companyName')}
          onChange={handleCompanyNameChange}
      />

      <input
          type="text"
          placeholder="sort by"
          value={search.get('sortBy')}
          onChange={handleSortBy}
      />


      <button onClick={handleSearchCustomers}>Search</button>
      <ul className="displayCustomers">
        {/* Mapping through each customer object in our customers array
            and returning a paragraph with thier first name last name and company name
        */}
        {customers.map((customer, index) => (
          <div className="customer" key={customer._id}>
            <li className="customer_element">
              <p>{customer.firstName}   {customer.lastName},  {customer.companyName}</p>
            </li>
          </div>
        ))}
      </ul>
    </div>
  );

};

 export default CustomerTable;
