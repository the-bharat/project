import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Address.css";
import config from "../../config";
import { toast } from "react-toastify";
import CustHomeNv from "./../../components/CustHomeNv";

const AddAddress = () => {
  const [line1, setLine1] = useState("");
  const [line2, setLine2] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [contactNo, setContactNo] = useState("");
  const [pincode, setPincode] = useState("");

  const navigate = useNavigate();

  const addUserAddress = (e) => {
    e.preventDefault();
    if (!line1) {
      toast.error("Please add Address line 1");
      return;
    } else if (!city) {
      toast.error("Please add city");
      return;
    } else if (!state) {
      toast.error("Please add state");
      return;
    } else if (!pincode) {
      toast.error("Please enter pincode");
      return;
    } else if (!contactNo) {
      toast.error("Please enter contact no");
      return;
    }

    const id = sessionStorage.getItem("customerId");
    const addressData = {
      line1,
      line2,
      city,
      state,
      contactNo,
      pincode,
    };

    axios
      .post(`${config.serverURL}/address/add/${id}`, addressData, {
        "Content-Type": "application/json",
      })
      .then(() => {
        toast.success("Address Added Successfully !!!");
        navigate("/SelectAddress");
      })
      .catch((error) => {
        toast.error("An error occurred!");
        console.error("There was an error adding the address:", error);
      });
  };

  return (
    <div className="container-fluid">
      <CustHomeNv />
      <section className="container-fluid myStyle1">
        <h3 className="mb-5 text-uppercase" style={{ textAlign: "center" }}>
          ADDRESS
        </h3>
        {/* Unique IDs for each input field */}
        <div className="form-outline mb-4">
          <label className="form-label" htmlFor="addressLine1">
            Address Line 1
          </label>
          <input
            type="text"
            id="addressLine1"
            className="form-control form-control-lg"
            value={line1}
            onChange={(e) => setLine1(e.target.value)}
          />
        </div>

        <div className="form-outline mb-4">
          <label className="form-label" htmlFor="addressLine2">
            Address Line 2
          </label>
          <input
            type="text"
            id="addressLine2"
            className="form-control form-control-lg"
            value={line2}
            onChange={(e) => setLine2(e.target.value)}
          />
        </div>

        <div className="form-outline mb-4">
          <label className="form-label" htmlFor="city">
            City
          </label>
          <input
            type="text"
            id="city"
            className="form-control form-control-lg"
            value={city}
            onChange={(e) => setCity(e.target.value)}
          />
        </div>

        <div className="form-outline mb-4">
          <label className="form-label" htmlFor="state">
            State
          </label>
          <input
            type="text"
            id="state"
            className="form-control form-control-lg"
            value={state}
            onChange={(e) => setState(e.target.value)}
          />
        </div>

        <div className="form-outline mb-4">
          <label className="form-label" htmlFor="contactNo">
            Contact No
          </label>
          <input
            type="text"
            id="contactNo"
            className="form-control form-control-lg"
            value={contactNo}
            onChange={(e) => setContactNo(e.target.value)}
          />
        </div>

        <div className="form-outline mb-4">
          <label className="form-label" htmlFor="pinCode">
            Pin Code
          </label>
          <input
            type="text"
            id="pinCode"
            className="form-control form-control-lg"
            value={pincode}
            onChange={(e) => setPincode(e.target.value)}
          />
        </div>

        <div style={{ display: "flex", justifyContent: "center" }}>
          <button
            onClick={addUserAddress}
            type="submit"
            className="btn btn-success"
          >
            Submit
          </button>
        </div>
      </section>
    </div>
  );
};

export default AddAddress;