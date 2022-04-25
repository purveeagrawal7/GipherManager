import React, {useState} from "react";
import "./gifList.css";
import { Button } from "react-bootstrap";
export default function GifList(props) {
  return (    
      <div className="col-md-4 col-sm-6 col-xs-12 gifList">
        <img className="card-img" src={props.eachGif.images ? props.eachGif.images.fixed_height.url : props.eachGif.url} 
         style={{ height: "250px", width: "265px" }}/>
        <p className="card-text ">{props.eachGif["title"]}</p>
        {props.showBookMarkButton ? (
          <Button
            variant="success"
            type="submit" 
            id="add-button"
            className="btn-success"
            onClick={() => props.addToBookmarkList(props)}
          >
            BookMark{" "}
          </Button>
        ) : (
          ""
        )}
          {props.showDeleteBookMarkButton ? (
          <Button
            variant="success"
            type="submit" 
            id="delete"
            className="btn-danger"
            onClick={() => props.deleteFromBookMarkList(props.eachGif.gifId)}
          >
            Remove from BookMark{" "}
          </Button>
        ) : (
          ""
        )}
      </div>  
  );
}