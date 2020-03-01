package Commands;

/**
 * Абстрактный класс команд. На его основе создается остальные команды.
 */
public abstract class Command {
    protected abstract void writeInfo();
    protected abstract void execute(String[] args);
}
