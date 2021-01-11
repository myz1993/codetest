import React, { useState, useEffect } from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { withStyles } from '@material-ui/styles';
import style from './style';
import { LABEL_DATA_URL, SUMMARY_DATA_URL } from '../../config/constants';
import { TableData, LabelDto, SummaryDto } from './interface';
import axios from 'axios';

const App = (props: any) => {
  const { classes } = props;
  const [cyptoData, setCyptoData] = useState<TableData[]>();

  const descendingComparator = <T extends unknown>(a: T, b: T, orderBy: keyof T) => {
    if (b[orderBy] < a[orderBy]) {
      return -1;
    }
    if (b[orderBy] > a[orderBy]) {
      return 1;
    }
    return 0;
  };

  const getData = async () => {
    const labelResult = await axios.get(LABEL_DATA_URL);
    const summaryResult = await axios.get(SUMMARY_DATA_URL);

    const { data: labelData } = labelResult;
    const { data: summaryData } = summaryResult;

    const result: TableData[] = [];

    labelData.coins.forEach((label: LabelDto) => {
      summaryData.forEach((summary: SummaryDto) => {
        if (label.id === summary.currency) {
          result.push({
            ...summary,
            rank: label.market_cap_rank,
            thumb: label.thumb,
            label: label.name,
          });
        }
      });
    });

    result.sort((a, b) => {
      return descendingComparator(a, b, 'marketCap');
    });

    setCyptoData(result);
  };

  useEffect(() => {
    getData();
  }, []);

  return (
    <div className={classes.root}>
      <TableContainer className={classes.container}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>#</TableCell>
              <TableCell>Coin</TableCell>
              <TableCell>Price</TableCell>
              <TableCell>24h</TableCell>
              <TableCell>7d</TableCell>
              <TableCell>24h Volume</TableCell>
              <TableCell>Mkt Cap</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {cyptoData &&
              cyptoData.map((item: TableData, index: number) => {
                const { price, day, week, volume, marketCap, label, thumb } = item;
                return (
                  <TableRow key={label}>
                    <TableCell>{index + 1}</TableCell>
                    <TableCell>
                      <img className={classes.icon} src={thumb} alt={label} />
                      {label}
                    </TableCell>
                    <TableCell>{price}</TableCell>
                    <TableCell className={day > 0 ? classes.up : classes.down}>{day}%</TableCell>
                    <TableCell className={week > 0 ? classes.up : classes.down}>{week}%</TableCell>
                    <TableCell>{volume}</TableCell>
                    <TableCell>{marketCap}</TableCell>
                  </TableRow>
                );
              })}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default withStyles(style)(App);
