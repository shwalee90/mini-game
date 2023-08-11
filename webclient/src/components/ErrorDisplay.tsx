import type { FC } from "react";
import { Row, Col } from "antd";
import Tutorial, { ComSubmitType, SubmitType } from "../routes/Tutorial";

export type ErrorDisplayProps = {
    errMsg: string[];
    score: string;
};

const ErrorDisplay: FC<ErrorDisplayProps> = ({ errMsg , score }) => {
  return  (
                <Row>
                    <Col span={12}>
                    {score === "score1Submit" &&
                    errMsg.length !== 0 ? (
                        errMsg.map((errMsg, index) => <p key={index}>{errMsg}</p>)
                    ) : (
                        <p></p>
                    )}
                    </Col>
                </Row>
          )
};

export default ErrorDisplay;
