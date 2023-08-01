import type { PropsWithChildren } from "react";
import type { ChangeEvent } from "react";
import { useState, useCallback, useEffect, useRef, FC } from "react";
import { Button, Icon } from "../theme/daisyui";
import { Row, Col } from "antd";
import Tutorial, {
  UserInfoType,
  BanInfoType,
  SubmitType,
} from "../routes/Tutorial";
import { post } from "../server";
import ProgressBar from "@ramonak/react-progress-bar";

export type ScoreBoardProps = {
  UserInfo: UserInfoType;
  BanInfo: BanInfoType;
  Submit: SubmitType;
  postTest: () => void;
};

const ScoreBoard: FC<ScoreBoardProps> = ({
  UserInfo,
  BanInfo,
  Submit,
  postTest,
}) => {
  const [count, setCount] = useState(10);

  // useEffect(() => {
  //   console.log("1");
  //   setPostData((obj) => ({
  //     ...obj,
  //     round: UserInfo.round,
  //     totalScore: UserInfo.totalScore,
  //     totalToken: UserInfo.totalToken,
  //     banList: BanInfo.banList,
  //     score1Submit: Submit.score1Submit,
  //     score2Submit: Submit.score2Submit,
  //     score3Submit: Submit.score3Submit,
  //   }));
  // }, [UserInfo, BanInfo, Submit]);

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
        <Col span={12}>
          <p> 라운드 : {UserInfo.round}</p>
          <p> 점수 : {JSON.stringify("")}</p>
          <p> 총 토큰 : {UserInfo.totalToken}</p>
          <p> 남은 토큰 : {UserInfo.token}</p>
          <p> 0 사용 횟수 : {BanInfo.zeroCnt} / 2</p>
          <Row>
            <p>SCORE1 : </p>{" "}
            {BanInfo.banList[0] === false && BanInfo.zeroCnt !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
          <Row>
            <p>SCORE2 : </p>{" "}
            {BanInfo.banList[1] === false && BanInfo.zeroCnt !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
          <Row>
            <p>SCORE3 : </p>{" "}
            {BanInfo.banList[2] === false && BanInfo.zeroCnt !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
        </Col>
        <Col span={12}>
          <p> 점수 : {JSON.stringify("")}</p>
          <p> 총 토큰 : {UserInfo.totalToken}</p>
          <p> 남은 토큰 : {UserInfo.token}</p>
        </Col>
      </Row>

      <div>
        <ProgressBar
          bgColor="blue"
          width="100%"
          height="40px"
          completed={String(count * 10)}
          customLabel={String(count)}
        ></ProgressBar>
        <span>{count}</span>
      </div>
    </div>
  );
};

export default ScoreBoard;
