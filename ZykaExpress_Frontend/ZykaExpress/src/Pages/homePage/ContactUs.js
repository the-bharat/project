import React from "react";

import CustHomeNv from "../../components/CustHomeNv";
import RestoNav from "../../components/RestoNav";
import NavbarDeliveryBoy from "../../components/navbarDeliveryBoy";
import "./ContactUs.css";

const ContactUs = () => {
  const contacts = [
    {
      name: "Vaibhav SIngh",
      email: "vaibbhav.gyn@gmail.com",
      phone: "9118008930"
    },
    {
      name: "Akash Kendre",
      email: "ak8858@gmail.com",
      phone: "9586214703"
     
    },
    
  ];

  return (
    <div className="container-fluid">
      {sessionStorage.getItem("role") === "CUSTOMER" && <CustHomeNv />}
      {sessionStorage.getItem("role") === "RESTAURANT" && <RestoNav />}
      {sessionStorage.getItem("role") === "DELIVERYBOY" && <NavbarDeliveryBoy />}
      
      <div className="container contactUsBackground">
        <h1 className="display-4 text-center text-purple">
          ZykaExpress
        </h1>
        <p className="description">
          Discover a seamless way to order your favorite dishes from nearby kitchens with this platform. The intuitive graphical interface allows users to effortlessly browse and select items from various restaurant menus. Administrators manage the platform, ensuring that restaurants can easily update their offerings. Users can conveniently add multiple items to their cart and choose from various payment methods to complete their orders. Delivery personnel can access their assigned orders along with detailed delivery addresses, streamlining the entire process from kitchen to doorstep.
        </p>
        <hr />
        <h2 className="text-center mt-4">Contact Us</h2>
        <div className="row mt-5 justify-content-center">
          {contacts.map((contact) => (
            <div className="col-lg-4 col-md-6 text-center mt-4" key={contact.name}>
              <img
                src={contact.image}
                alt={contact.name}
                className="img-fluid rounded-circle contact-img"
              />
              <h3 className="mt-3 contact-name">{contact.name}</h3>
              <p className="text-muted contact-info">
                {contact.email}
                <br />
                {contact.phone}
              </p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ContactUs;
