import { removeAuthorisationHeader } from "../security/RemoveAuthorisationHeader";
import "./navbar.css";

const CustHomeNv = () => {
  var x = sessionStorage.getItem("customerName");

  const logoutMethod = () => {
    sessionStorage.clear();
    removeAuthorisationHeader();
  };
  return (
    <nav className="navbar navbar-expand-lg py-2 navbar-dark bg-dark shadow-sm">
      <div className="container-fluid">
        <a className="navbar-brand" href="/CustomerHomePage">
          <b style={{ color: "#F7BC0F", fontSize: 30 }}>ZykaExpress</b>
        </a>
        <div id="navbarSupportedContent" className="collapse navbar-collapse">
          <ul className="navbar-nav ml-auto">
            <li className="nav-item">
              <a href="/Cart" className="nav-link">
                <i className="fas fa-shopping-cart"></i>
                &nbsp; Cart
              </a>
            </li>
            <li className="nav-item">
              <a href="/MyOrders" className="nav-link">
                <i className="fas fa-concierge-bell"></i>
                &nbsp; MyOrders
              </a>
            </li>

            <li className="nav-item">
              <a href="/AddFeedback" className="nav-link">
                <i className="fas fa-edit"></i>
                &nbsp;Feedback
              </a>
            </li>

            <li className="nav-item">
              <a href="/contactUs" className="nav-link">
                Contact Us
              </a>
            </li>

            <div className="btn-group mybtn-right">
              <button
                type="button"
                className="btn btn-dark dropdown-toggle"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <i className="fas fa-user"></i>
                &nbsp; {x ? x : "Welcome"}
              </button>
              <ul className="dropdown-menu dropdown-menu-end">
                <li>
                  <a className="dropdown-item" href="/updateProfile">
                    Update Profile
                  </a>
                </li>
                <li>
                  <a className="dropdown-item" href="/signin" onClick={logoutMethod}>
                    LogOut
                  </a>
                </li>
              </ul>
            </div>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default CustHomeNv;
