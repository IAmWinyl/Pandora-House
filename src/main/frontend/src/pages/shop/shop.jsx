import React, { Component } from "react";
import './shop.css';
import item1 from 'assets/1.jpg';
import item2 from 'assets/2.jpg';
import item3 from 'assets/3.jpg';
import item4 from 'assets/4.jpg';
import item5 from 'assets/5.jpg';
import item6 from 'assets/6.jpg';
import item7 from 'assets/7.jpg';
import item8 from 'assets/8.jpg';
import item9 from 'assets/1.jpg';
import item10 from 'assets/2.jpg';
import item11 from 'assets/3.jpg';
import item12 from 'assets/4.jpg';
import item13 from 'assets/5.jpg';
import item14 from 'assets/6.jpg';
import item15 from 'assets/7.jpg';
import item16 from 'assets/8.jpg';

// Mock list of images
const imagesList = [
    {
      id: 1,
      src: item1,
      alt: "item1",
    },
    {
      id: 2,
      src: item2,
      alt: "item2",
    },
    {
      id: 3,
      src: item3,
      alt: "item3",
    },
    {
      id: 4,
      src: item4,
      alt: "item4",
    },
    {
      id: 5,
      src: item5,
      alt: "item5",
    },
    {
      id: 6,
      src: item6,
      alt: "item6",
    },
    {
      id: 7,
      src: item7,
      alt: "item7",
    },
    {
      id: 8,
      src: item8,
      alt: "item8",
    },
    {
      id: 9,
      src: item9,
      alt: "item9",
    },
    {
      id: 10,
      src: item10,
      alt: "item10",
    },
    {
      id: 11,
      src: item11,
      alt: "item11",
    },
    {
      id: 12,
      src: item12,
      alt: "item12",
    },
    {
      id: 13,
      src: item13,
      alt: "item13",
    },
    {
      id: 14,
      src: item14,
      alt: "item14",
    },
    {
      id: 15,
      src: item15,
      alt: "item15",
    },
    {
      id: 16,
      src: item16,
      alt: "item16",
    },
  ];

export default class Shop extends Component {
    state = { imagesList }

    getImages() {
        let image = imagesList[0];
        console.log(imagesList);
        return ( this.state.imagesList.map(function(image) {
            return (<div className="product__image"><img key={image.key} src={image.src} alt={image.alt} /></div>)
        }) )
    }

    render () {
        return (
            <div className="shop section__padding">
                <div className="shop__sidebar">
                    <div className="list">
                        <h2>Sort</h2>
                        <h4>Category</h4>
                        <ul> 
                            <li><a href="/">T-shirts</a></li>
                            <li><a href="/">Pants</a></li>
                            <li><a href="/">Shorts</a></li>
                            <li><a href="/">Skirts</a></li>
                            <li><a href="/">Dresses</a></li>
                            <li><a href="/">Accessories</a></li>
                        </ul>
                    </div>
                </div>
                <div className="shop__content">
                    {this.getImages()}
                </div>
            </div>
        )
    }
}