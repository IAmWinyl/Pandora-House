import React from "react";
import './navbar.css';


const Navbar = () => {
    return (
        <div className="navbar__container section__padding">
            <div className="navbar__list">
                <nav>
                    <div><a href="/">New</a></div>
                    <div><a href="/">Bestsellers</a></div>
                    <div><a href="/">Clothing</a></div>
                    <div><a href="/">Shoes</a></div>
                    <div><a href="/">Accessories</a></div>
                    <div><a href="/">Beauty</a></div>
                    <div><a href="/">Home</a></div>
                    <div><a href="/">Sale</a></div>
                </nav>
            </div>
        </div>
    )
}

export default Navbar