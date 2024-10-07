import { removeAuthorisationHeader } from "../security/RemoveAuthorisationHeader";
const NavbarDeliveryBoy = () => {

  const logoutMethod = () => {
    sessionStorage.clear();
    removeAuthorisationHeader();
  };
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
      <div className="container-fluid">
        <a className="navbar-brand" href="/deliveryBoyHome">
          <b style={{ color: "#F7BC0F", fontSize: 30 }}>ZykaExpress</b>
        </a>
        <button
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
          class="navbar-toggler"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <div id="navbarSupportedContent" class="collapse navbar-collapse">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a href="/acceptedOrder" class="nav-link active">
                My Orders
              </a>

            </li>
            <li class="nav-item">
              <a href="/contactUs" class="nav-link">
                Contact Us
              </a>
            </li>

            <div className="btn-group" class="mybtn-right">
              <button
                type="button"
                class="btn btn-dark dropdown-toggle"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <i class="fas fa-user"></i>
                &nbsp; {sessionStorage.getItem("customerName")}
              </button>
              <ul class="dropdown-menu dropdown-menu-end">
                <li>
                  <a class="dropdown-item" href="/updateProfile">
                    Update Profile
                  </a>
                </li>
                <li>
                  <a class="dropdown-item" href="/signin" onClick={logoutMethod}>
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
export default NavbarDeliveryBoy;
