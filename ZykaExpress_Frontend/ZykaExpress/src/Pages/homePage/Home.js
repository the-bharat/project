import homeImg from "../../images/pexels-julieaagaard-1426715.jpg";
import "./home.css";
import { useNavigate } from "react-router-dom";

const Home = () => {
    const navigate = useNavigate();

    const loginUser = () => {
        navigate('/signin');
    };
    const SignupUser = () => {
        navigate('/signup');
    };
    const RegisterRest = () => {
        navigate('/registerRestaurant');
    };

    return (
        <div className="container-fluid home-container"
            style={{
                backgroundImage: `url(${homeImg})`,
                backgroundSize: 'cover',
                backgroundPosition: 'center',
                height: '100vh',
                display: 'flex',
                flexDirection: 'column',
            }}>

            <nav className="navbar navbar-expand-lg navbar-light bg-light p-3">
                <div className="container-fluid">
                    <a className="navbar-brand logo1" href="#"><b>ZykaExpress</b></a>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav ms-auto">
                            <li className="nav-item">
                                <button className="btn btn-outline-dark me-3" onClick={loginUser}>
                                    Login
                                </button>
                            </li>
                            <li className="nav-item">
                                <button className="btn btn-warning" onClick={SignupUser}>
                                    Sign Up
                                </button>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <div className="row align-items-center justify-content-center text-center text-overlay">
                <div className="col-md-8">
                    <h1 className="display-4 foodi">Welcome to ZykaExpress</h1>
                    <p className="lead text1">
                        TThis platform enables users to order a wide range of cuisines from nearby cloud kitchens with ease. The intuitive interface allows customers to browse through various dishes and place orders seamlessly. Administrators manage the platform, ensuring that cloud kitchens can display their menu options effectively. Users can effortlessly add items to their carts for a smooth ordering experience. Delivery personnel are provided with assigned orders and delivery addresses, ensuring prompt and efficient service.
                    </p>
                </div>
            </div>

            <div className="text-center">
                <a href="/contactUs" className="btn btn-outline-light link1">
                    Contact Us
                </a>
            </div>
        </div>
    );
}

export default Home;
