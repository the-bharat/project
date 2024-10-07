import { Link } from "react-router-dom";
const Header = () => {
  return (
    <div
      className="container-fluid"
      style={{ textAlign: "center", backgroundColor: "black" }}
    >
      <Link
        to="/adminHome"
        className="navbar-brand"
        style={{ textDecoration: "none" }}
      >
        <b style={{ color: "#F7BC0F", fontSize: "40px" }}>ZykaExpress</b>
      </Link>
    </div>
  );
};

export default Header;