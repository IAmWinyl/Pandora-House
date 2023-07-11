import React from "react";
import './footer.css';


const Footer = () => {
    return (
        <div>
            <div className="footer">
                <div className="footer__nav">
                    <ul>
                        <li>About</li>
                        <li>Returns</li>
                        <li>FAQ</li>
                        <li>Contact Us</li>
                        <li>Terms &amp; Conditions</li>
                    </ul>
                </div>
                <div className="footer__copyright">
                    <p>Copyright &copy; 2023</p>
                </div>
                <div className="footer__socialmedia">
                    <a className="footer__instagramlogo" href="#" title="Instagram">
                    </a>
                </div>
            </div>
        </div>
    )
}

export default Footer