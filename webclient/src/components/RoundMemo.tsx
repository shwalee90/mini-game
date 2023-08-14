import type { FC } from "react";
import { Row, Col } from "antd";
import Tutorial, { ResultMemoType } from "../routes/Tutorial";
import HorizonLine from "./HorizonLine";

export type RoundResultProps = {
  scoreResult: ResultMemoType;
};

const RoundMemo: FC<RoundResultProps> = ({ scoreResult }) => {
  let endRound = scoreResult.endRound;

  return (
    <div>
      <div>
        <p className="p-4 text-xl text-left ">지난 라운드 결과</p>
      </div>

      <div>
        <Row>
          {endRound >= 1 ? (
            <Col span={8}>
              <p>round1 결과</p>
              <Row>
                <Col span={8}>
                  <p></p>
                </Col>
                <Col span={8}>
                  <p>나의 배팅</p>
                </Col>
                <Col span={8}>
                  <p>상대방 배팅</p>
                </Col>
              </Row>
              <Row>
                <Col span={8}>
                  <p>SCORE1 배팅</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.resultMemo[0][0]}</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.comResultMemo[0][0]}</p>
                </Col>
              </Row>
              <Row>
                <Col span={8}>
                  <p>SCORE2 배팅</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.resultMemo[0][1]}</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.comResultMemo[0][1]}</p>
                </Col>
              </Row>
              <Row>
                <Col span={8}>
                  <p>SCORE3 배팅</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.resultMemo[0][2]}</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.comResultMemo[0][2]}</p>
                </Col>
              </Row>
              <HorizonLine text={""}></HorizonLine>
              <Row>
                <Col span={8}>
                  <p>Round1 합산 결과</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.thisRoundResult[0]}</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.thisRoundResult[1]}</p>
                </Col>
              </Row>
            </Col>
          ) : null}
          {endRound >= 2 ? (
            <Col span={8}>
              <p>round2 결과</p>
              <Row>
                <Col span={8}>
                  <p></p>
                </Col>
                <Col span={8}>
                  <p>나의 배팅</p>
                </Col>
                <Col span={8}>
                  <p>상대방 배팅</p>
                </Col>
              </Row>
              <Row>
                <Col span={8}>
                  <p>SCORE1 배팅</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.resultMemo[1][0]}</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.comResultMemo[1][0]}</p>
                </Col>
              </Row>
              <Row>
                <Col span={8}>
                  <p>SCORE2 배팅</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.resultMemo[1][1]}</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.comResultMemo[1][1]}</p>
                </Col>
              </Row>
              <Row>
                <Col span={8}>
                  <p>SCORE3 배팅</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.resultMemo[1][2]}</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.comResultMemo[1][2]}</p>
                </Col>
              </Row>
              <HorizonLine text={""}></HorizonLine>
              <Row>
                <Col span={8}>
                  <p>Round2 합산 결과</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.thisRoundResult[0]}</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.thisRoundResult[1]}</p>
                </Col>
              </Row>
            </Col>
          ) : null}
          {endRound >= 3 ? (
            <Col span={8}>
              <p>round3 결과</p>
              <Row>
                <Col span={8}>
                  <p></p>
                </Col>
                <Col span={8}>
                  <p>나의 배팅</p>
                </Col>
                <Col span={8}>
                  <p>상대방 배팅</p>
                </Col>
              </Row>
              <Row>
                <Col span={8}>
                  <p>SCORE1 배팅</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.resultMemo[2][0]}</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.comResultMemo[2][0]}</p>
                </Col>
              </Row>
              <Row>
                <Col span={8}>
                  <p>SCORE2 배팅</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.resultMemo[2][1]}</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.comResultMemo[2][1]}</p>
                </Col>
              </Row>
              <Row>
                <Col span={8}>
                  <p>SCORE3 배팅</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.resultMemo[2][2]}</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.comResultMemo[2][2]}</p>
                </Col>
              </Row>
              <HorizonLine text={""}></HorizonLine>
              <Row>
                <Col span={8}>
                  <p>Round3 합산 결과</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.thisRoundResult[0]}</p>
                </Col>
                <Col span={8}>
                  <p>{scoreResult.thisRoundResult[1]}</p>
                </Col>
              </Row>
            </Col>
          ) : null}
        </Row>
      </div>
    </div>
  );
};

export default RoundMemo;
