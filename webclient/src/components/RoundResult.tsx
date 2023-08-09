import type { FC } from "react";
import { Row, Col } from "antd";
export type RoundResultProps = {
  scoreResult: number;
};

export const RoundResult: FC<RoundResultProps> = ({}) => {
  return <Col span={4}></Col>;
};
