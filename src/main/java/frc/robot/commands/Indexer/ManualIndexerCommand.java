package frc.robot.commands.Indexer;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerSubsystem;

public class ManualIndexerCommand extends CommandBase {
  private IndexerSubsystem indexerSubsystem;

  private NetworkTableEntry speed;

  public ManualIndexerCommand(IndexerSubsystem indexerSubsystem) {
    this.indexerSubsystem = indexerSubsystem;
    addRequirements(indexerSubsystem);

  }

  @Override
  public void initialize() {
    speed = IndexerSubsystem.tab.add("Indexer Speed (button B)", 0.5).getEntry();
  }

  @Override
  public void execute() {
    indexerSubsystem.turnBothIndexMotors(speed.getDouble(0.5));
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
