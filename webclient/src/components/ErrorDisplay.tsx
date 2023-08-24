import type { FC } from "react";
import { Row, Col } from "antd";
import Tutorial, { ComSubmitType, SubmitType } from "../routes/Tutorial";

export type ErrorDisplayProps = {
  errMsg: string[];
};

const ErrorDisplay: FC<ErrorDisplayProps> = ({ errMsg }) => {
  return (
    <Row>
      <Col span={12}>
        {errMsg.map((errMsg, index) => (
          <p key={index}>{errMsg}</p>
        ))}
      </Col>
    </Row>
  );
  //         <Row>
  //         <Col span={12}>
  //         {score === "score1Submit" &&
  //         errMsg.length !== 0 ? (
  //             errMsg.map((errMsg, index) => <p key={index}>{errMsg}</p>)
  //         ) : (
  //             <p></p>
  //         )}
  //         </Col>
  //     </Row>
  //   )
};

export default ErrorDisplay;
