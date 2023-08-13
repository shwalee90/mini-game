import type { FC } from "react";
import { Row, Col } from "antd";
import Tutorial, {
  ResultMemoType,
} from "../routes/Tutorial";

export type RoundResultProps = {
  scoreResult: ResultMemoType;
};

const RoundMemo: FC<RoundResultProps> = ({scoreResult}) => {
  
  let endRound = scoreResult.endRound
  
  return (
  <div>
    <div>
      <p className="p-4 text-xl text-left ">지난 라운드 결과</p>
    </div>

    <div>
    <Row>
      { 
      endRound >= 1 ?
      <Col span={8}>
        <p>round1</p>
      </Col>
      : null
      }
      { 
      endRound >= 2 ?
      <Col span={8}>
        <p>round2</p>
      </Col>
      : null
      }
      { 
      endRound >= 3 ?
      <Col span={8}>
        <p>round3</p>
      </Col>
      : null
      }
    </Row>
    </div> 
  </div>  
  )
};

export default RoundMemo;
