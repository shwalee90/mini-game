import type { FC } from "react";
import { Row, Col } from "antd";
export type RoundResultProps = {
  scoreResult: number;
};

const ResultMemo: FC<RoundResultProps> = ({}) => {
  return <Col span={4}></Col>;
};

export default ResultMemo;
