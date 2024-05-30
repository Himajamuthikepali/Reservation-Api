import { Link } from "react-router-dom";
import '../Styles/LandingPage.css'
const LandingPage = () => {
    return(
        <div className="landingpage">
            <Link to="/adminlogin">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzCXI5IobC4QUmk6TLiL06LcjD8oglMo3rSA&usqp=CAU" alt="" />
                <h1>Admin</h1>
            </Link>
            <Link to="/userlogin">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmrc-UBHOT2_ToDH02MQWc7ZGYLtLB8xPyMw&usqp=CAU" alt="" />
                <h1>User</h1>
            </Link>
        </div>
    )
}
export default LandingPage;