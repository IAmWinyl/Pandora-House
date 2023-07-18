import React from "react";
import './shop_item.css';
//import 'assets/the-row-yellow-and-green-copo-maxi-dress.jpg';
//import 'assets/the-row-wrestler-shirt.png';

const Shop_Item = ({item}) => {
    let imgUrl = "/assets/" + item.imgUrl;

    return (
        <div className="shop__item__container">
          <img key={item.id} src={imgUrl} alt={item.alt} />
          <p>{item.name}</p>
          <p>{item.price}</p>
        </div>
      );
}

export default Shop_Item