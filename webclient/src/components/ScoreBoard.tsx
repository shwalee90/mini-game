import type { PropsWithChildren } from "react";
import type { ChangeEvent } from "react";
import { useState, useCallback, useEffect, useRef, FC } from "react";
import { Button, Icon } from "../theme/daisyui";
import { Row, Col } from "antd";
import Tutorial, {
  UserInfoType,
  BanInfoType,
  SubmitType,
  ComInfoType,
} from "../routes/Tutorial";
import { post } from "../server";
import ProgressBar from "@ramonak/react-progress-bar";

export type ScoreBoardProps = {
  UserInfo: UserInfoType;
  BanInfo: BanInfoType;
  Submit: SubmitType;
  ComInfo: ComInfoType;
  postTest: () => void;
};

const ScoreBoard: FC<ScoreBoardProps> = ({
  UserInfo,
  BanInfo,
  Submit,
  ComInfo,
  postTest,
}) => {
  const [count, setCount] = useState(10);

  useEffect(() => {
    // 설정된 시간 간격마다 setInterval 콜백이 실행된다.
    const id = setInterval(() => {
      // 타이머 숫자가 하나씩 줄어들도록
      setCount((count) => count - 1);
    }, 1000);

    // 0이 되면 카운트가 멈춤
    if (count === 0) {
      clearInterval(id);
      postTest();
    }
    return () => clearInterval(id);
    // 카운트 변수가 바뀔때마다 useEffecct 실행
  }, [count]);

  return (
    <div>
      <Row>
        <p> 라운드 : {UserInfo.round}</p>
      </Row>
      <Row>
        <Col span={12}>
          <p> 점수 : {UserInfo.totalScore}</p>
        </Col>
        <Col span={12}>
          <p> 점수 : {ComInfo.totalScore}</p>
        </Col>
      </Row>
      <Row>
        <Col span={12}>
          <p> 총 토큰 : {UserInfo.totalToken}</p>
        </Col>
        <Col span={12}>
          <p> 총 토큰 : {ComInfo.totalToken}</p>
        </Col>
      </Row>
      <Row>
        <Col span={12}>
          <p> 남은 토큰 : {UserInfo.token}</p>
        </Col>
      </Row>
      <Row>
        <Col span={12}>
          <p> 0 사용 횟수 : {BanInfo.zeroCnt[0]} / 2</p>
        </Col>
        <Col span={12}>
          <p> 0 사용 횟수 : {BanInfo.zeroCnt[1]} / 2</p>
        </Col>
      </Row>
      <Row>
        <Col span={12}>
          <Row>
            <p>SCORE1 : </p>{" "}
            {BanInfo.banList[0][0] === false && BanInfo.zeroCnt[0] !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
        </Col>
        <Col span={12}>
          <Row>
            <p>SCORE1 : </p>{" "}
            {BanInfo.banList[1][0] === false && BanInfo.zeroCnt[1] !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
        </Col>
      </Row>
      <Row>
        <Col span={12}>
          <Row>
            <p>SCORE2 : </p>{" "}
            {BanInfo.banList[0][1] === false && BanInfo.zeroCnt[0] !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
        </Col>
        <Col span={12}>
          <Row>
            <p>SCORE2 : </p>{" "}
            {BanInfo.banList[1][1] === false && BanInfo.zeroCnt[1] !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
        </Col>
      </Row>
      <Row>
        <Col span={12}>
          <Row>
            <p>SCORE3 : </p>{" "}
            {BanInfo.banList[0][2] === false && BanInfo.zeroCnt[0] !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
        </Col>
        <Col span={12}>
          <Row>
            <p>SCORE3 : </p>{" "}
            {BanInfo.banList[1][2] === false && BanInfo.zeroCnt[1] !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
        </Col>
      </Row>

      <div className="my-3">
        <ProgressBar
          bgColor="blue"
          width="100%"
          height="40px"
          completed={String(count * 10)}
          customLabel={String(count)}
        ></ProgressBar>
      </div>
    </div>
  );
};

export default ScoreBoard;
