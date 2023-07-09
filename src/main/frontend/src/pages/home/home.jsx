import React from "react";
import './home.css';
import front_page_img from '../../../public/assets/frontpage.png';

const Home = () => {
    return (
        <div className="home">
            <div className="home__image">
                <img src={front_page_img} />
            </div>
        </div>
    )
}

export default Home