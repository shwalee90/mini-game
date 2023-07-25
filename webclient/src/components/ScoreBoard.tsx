import type { ChangeEvent } from "react";
import { useState, useCallback, useEffect ,useRef ,FC} from "react";
import { Button, Icon } from "../theme/daisyui";
import { Row, Col } from "antd";
import Tutorial, {    UserInfoType} from "../routes/Tutorial";


const ScoreBoard = (
     {zeroCnt ,banScore1 , banScore2, banScore3 ,token,totalToken} :UserInfoType
) =>
    {

  return (
    <div>
      <Row>
        <Col span={12}>
          <p> 점수 : {JSON.stringify("")}</p>
          <p> 총 토큰 : {totalToken}</p>
          <p> 남은 토큰 : {token}</p>
          <p> 0 사용 횟수 : {zeroCnt} / 2</p>
          <Row>
            <p>SCORE1  : </p>{" "}
            {banScore1 === false && zeroCnt !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
          <Row>
            <p>SCORE2  :  </p>{" "}
            {banScore2 === false && zeroCnt !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
          <Row>
            <p>SCORE3  :  </p>{" "}
            {banScore3 === false && zeroCnt !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
        </Col>
        <Col span={12}>
          <p> 점수 : {JSON.stringify("")}</p>
          <p> 총 토큰 : {totalToken}</p>
          <p> 남은 토큰 : {token}</p>
        </Col>
      </Row>
      </div>       
  );
}


export default ScoreBoard;
