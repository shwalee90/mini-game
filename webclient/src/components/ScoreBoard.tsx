import type { PropsWithChildren } from "react";
import type { ChangeEvent } from "react";
import { useState, useCallback, useEffect ,useRef ,FC} from "react";
import { Button, Icon } from "../theme/daisyui";
import { Row, Col } from "antd";
import Tutorial, {    UserInfoType, BanInfoType} from "../routes/Tutorial";
export type ScoreBoardProps = {
  UserInfoType : UserInfoType;
  BanInfoType : BanInfoType;
};


const ScoreBoard :FC<ScoreBoardProps> = ({
  UserInfoType  , BanInfoType
}) =>
    {
      
  return (
    <div>
      <Row>
        <Col span={12}>
          <p> 라운드 : {UserInfoType.round }</p>
          <p> 점수 : {JSON.stringify("")}</p>
          <p> 총 토큰 : {UserInfoType.totalToken}</p>
          <p> 남은 토큰 : {UserInfoType.token}</p>
          <p> 0 사용 횟수 : {   BanInfoType.zeroCnt} / 2</p>
          <Row>
            <p>SCORE1  : </p>{" "}
            {BanInfoType.banScore1 === false && BanInfoType.zeroCnt !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
          <Row>
            <p>SCORE2  :  </p>{" "}
            {BanInfoType.banScore2 === false && BanInfoType.zeroCnt !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
          <Row>
            <p>SCORE3  :  </p>{" "}
            {BanInfoType.banScore3 === false && BanInfoType.zeroCnt !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
        </Col>
        <Col span={12}>
          <p> 점수 : {JSON.stringify("")}</p>
          <p> 총 토큰 : {UserInfoType.totalToken}</p>
          <p> 남은 토큰 : {UserInfoType.token}</p>
        </Col>
      </Row>
      </div>       
  );
}


export default ScoreBoard;
