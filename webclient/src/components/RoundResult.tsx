import type { FC } from "react";
import { Row, Col } from "antd";
import Tutorial, { ComSubmitType, SubmitType } from "../routes/Tutorial";

export type RoundResultProps = {
  resultMsg: string;
};

const RoundResult: FC<RoundResultProps> = ({ resultMsg }) => {
  return  (<Row>
            <Col span={12}>
                <p>{resultMsg}</p>
            </Col>
           </Row>
          )
};

export default RoundResult;
