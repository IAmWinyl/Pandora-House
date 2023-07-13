import React, { Component } from "react";
import './home.css';
import { Outlet } from 'react-router-dom';


import front_page_img from 'assets/frontpage.png';
import runway1 from 'assets/runway1.webp';
import runway2 from 'assets/runway2.webp';
import runway3 from 'assets/runway3.webp';

export default class Home extends Component {
    render () {
        return (
            <div className="home">
                <div className="featuredcontent">
                    <div className="featuredcontent__image">
                        <img src={front_page_img} />
                    </div>
                    <div className="featuredcontent__details section__padding">
                        <h1>Lorem Ipsum</h1>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.</p>
                        <a href="/">Go &#62;</a>
                    </div>
                </div>
                <div className="content section__padding">
                    <h1>Just Arrived</h1>
                    <div className="content__image">
                        <img src={runway1} />
                        <img src={runway2} />
                        <img src={runway3} />
                    </div>
                    <a href="/"><p>Discover the new collection</p></a>
                    <Outlet />
                </div>
            </div>
        )
    }
}