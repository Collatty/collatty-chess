package com.collatty.chess.engine.player;

import com.collatty.chess.engine.Alliance;
import com.collatty.chess.engine.board.Board;
import com.collatty.chess.engine.board.Move;
import com.collatty.chess.engine.board.Tile;
import com.collatty.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WhitePlayer extends Player{

    public WhitePlayer(final Board board, final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals) {

        final List<Move> kingCastles = new ArrayList<Move>();

        if(this.playerKing.isFirstMove() && !this.isInCheck()) {
            if(!this.board.getTile(61).isTileOccupied() && !this.board.getTile(62).isTileOccupied()){
                final Tile rookTile = this.board.getTile(63);

                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {

                    if(Player.calculateAttacksOnTile(61, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(62, opponentLegals).isEmpty() &&
                        rookTile.getPiece().getPieceType().isRook()){
                        kingCastles.add(null); //TODO: add move
                    }

                }
            }


            if(!this.board.getTile(59).isTileOccupied() && !this.board.getTile(58).isTileOccupied() &&
                !this.board.getTile(57).isTileOccupied()){

                final Tile rookTile = this.board.getTile(56);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                    kingCastles.add(null); //TODO add move here also
                }
            }
        }



        return ImmutableList.copyOf(kingCastles);
    }
}
